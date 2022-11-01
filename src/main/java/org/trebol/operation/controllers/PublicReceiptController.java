/*
 * Copyright (c) 2022 The Trebol eCommerce Project
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software
 * and associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense,
 * and/or sell copies of the Software, and to permit persons to whom the Software is furnished
 * to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial
 * portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE
 * SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package org.trebol.operation.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.trebol.operation.IReceiptService;
import org.trebol.pojo.ReceiptPojo;

import javax.persistence.EntityNotFoundException;

@CrossOrigin
@RestController
@RequestMapping("/public/receipt")
public class PublicReceiptController {
  private final Logger LOG = LoggerFactory.getLogger(PublicReceiptController.class);

  private final IReceiptService receiptService;

  @Autowired
  public PublicReceiptController(IReceiptService receiptService) {
    this.receiptService = receiptService;
  }

  @GetMapping({"/{token}", "/{token}/"})
  public ReceiptPojo fetchReceiptById(@PathVariable("token") String token)
      throws EntityNotFoundException {
    if (token == null) {
      throw new RuntimeException("An incorrect receipt token was provided");
    }
    return this.receiptService.fetchReceiptByTransactionToken(token);
  }
}
